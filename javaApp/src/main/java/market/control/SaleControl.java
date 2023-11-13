package market.control;

import market.model.Sale;
import market.model.User;
import market.repository.SaleRepository;
import market.repository.UserRepository;

public class SaleControl extends Control<Sale>{

	
	public SaleControl(SaleRepository repo) {
		super(repo);
	}

	@Override
	public void save(Sale pSale) {
		if(repository.findById(pSale.getId()) == null)
		{
			repository.save(pSale);
			System.out.println("se guardo: nueva venta!");
		}
	}

	@Override
	public void delete(Sale pSale) {
		repository.delete(pSale.getId());
		System.out.println("se guardo: nueva venta!");
	}
	
	public void giveCommisions(Sale pSale) {
		for(User user : pSale.getUsersToGiveCommission()) {
			double newAmount = user.getCommision()*pSale.getPrice();
			user.addToWallet(newAmount);
			UserControl userControl = new UserControl(UserRepository.getInstance());
			userControl.updateValues(user);
		}
		save(pSale);
	}
	

}
