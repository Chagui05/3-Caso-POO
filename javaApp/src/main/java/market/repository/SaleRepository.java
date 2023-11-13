package market.repository;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;

import market.model.Review;
import market.model.Sale;
import redis.clients.jedis.Jedis;

public class SaleRepository implements IRepository<Sale>{
	
	private static volatile SaleRepository instance;
	private ObjectMapper objectMapper;
	private Jedis jedis;
	
	private SaleRepository() {
		jedis = ConnectDBRepository.getInstance().getJedis();
		objectMapper = new ObjectMapper();
	}
	
	// constructor singleton
	public static SaleRepository getInstance() {
		SaleRepository result = instance;
		if(result == null) {
			synchronized(SaleRepository.class) {
				result = instance;
				if(result == null) {
					instance = result = new SaleRepository();
				}
			}
		}
		return result; 
	}
	
	@Override
    public void save(Sale sale) {
		try {
            String saleJson = objectMapper.writeValueAsString(sale);
            jedis.hset("saleHash", "sale:" + sale.getId(), saleJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

    @Override
    public Sale findById(int id) {
    	String saleJson = jedis.hget("saleHash", "sale:" + id);
        if (saleJson != null) {
            try {
                return objectMapper.readValue(saleJson, Sale.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

	@Override
	public Vector<Sale> findAll() {
		Vector<Sale> sales = new Vector<>();
		Map<String, String> saleHash = jedis.hgetAll("saleHash");
		
		for (Map.Entry<String, String> entry : saleHash.entrySet()) {
            String saleJson = entry.getValue();
            try {
                Sale sale = objectMapper.readValue(saleJson, Sale.class);
                sales.add(sale);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sales;
	}

	@Override
	public void delete(int id) {
		String key = "sale:" + id;
		jedis.hdel("saleHash", key);
	}


}