const BlogPosts = [];

class BlogPost {
    constructor(image, user, date,title, description, price, purchaseLocation, commissionPercentage) {
      this.image = image;
      this.user = user;
      this.date = date;
      this.title = title;
      this.description = description;
      this.price = price;
      this.purchaseLocation = purchaseLocation;
      this.commissionPercentage = commissionPercentage;
      this.reviews = [];
    }
  
    addReview(review) {
      this.reviews.push(review);
    }
  
    getAverageRating() {
      if (this.reviews.length === 0) {
        return 0;
      }
  
      const totalRating = this.reviews.reduce((sum, review) => sum + review.rating, 0);
      return totalRating / this.reviews.length;
    }
  }
  
  class Review {
    constructor(user, rating, comment) {
      this.user = user;
      this.rating = rating;
      this.comment = comment;
    }
  }
  
  const blogPosts = [];

  // Crear varios objetos BlogPost y agregarlos al arreglo
  const post1 = new BlogPost('image1.jpg', 'Usuario1', new Date(), 'Descripción del Producto 1', 29.99, 'TiendaA', 3);
  const post2 = new BlogPost('image2.jpg', 'Usuario2', new Date(), 'Descripción del Producto 2', 39.99, 'TiendaB', 4);
  const post3 = new BlogPost('image3.jpg', 'Usuario3', new Date(), 'Descripción del Producto 3', 49.99, 'TiendaC', 5);
  
  blogPosts.push(post1, post2, post3);
  
  // Esta función muestra los posts en la página
  function displayPosts() {
    console.log('La función displayPosts se ha ejecutado.');
    const postList = document.getElementById('post-list');
  
    blogPosts.forEach((post) => {
      const listItem = document.createElement('li');
      listItem.innerHTML = `
        <img src="${post.image}" alt="Producto">
        <h2>${post.description}</h2>
        <p>Usuario: ${post.user}</p>
        <p>Fecha: ${post.date.toDateString()}</p>
        <p>Precio: $${post.price}</p>
        <p>Lugar de compra: ${post.purchaseLocation}</p>
        <p>Comisión: ${post.commissionPercentage}%</p>
      `;
  
      postList.appendChild(listItem);
    });
  }
  
  // Ejecuta la función para mostrar los posts una vez que la página esté completamente cargada
  window.addEventListener('load', displayPosts);
  