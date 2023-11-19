const express = require('express');
const app = express();
const port = 5500; // Replace with your desired port

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
app.get('/blog/:postTitle', (req, res) => {
    const postTitle = req.params.postTitle; // Extract the post title from the URL
    res.sendFile(__dirname + `/blog/${postTitle}.html`); // Serve the HTML page
});

app.use(express.json());

// Endpoint para recibir un JSON mediante una solicitud POST en la ruta /posts
app.get('/Posts', (req, res) => {
  const postData = req.body; // Datos enviados en la solicitud POST

  // Realiza alguna lógica con los datos (en este ejemplo, solo se envía una respuesta con los datos recibidos)
  res.json({ message: 'Datos recibidos correctamente', data: postData });
});