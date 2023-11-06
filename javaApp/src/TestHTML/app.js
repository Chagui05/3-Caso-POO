const express = require('express');
const app = express();
const port = 3000; // Replace with your desired port

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
app.get('/blog/:postTitle', (req, res) => {
    const postTitle = req.params.postTitle; // Extract the post title from the URL
    res.sendFile(__dirname + `/blog/${postTitle}.html`); // Serve the HTML page
  });
  