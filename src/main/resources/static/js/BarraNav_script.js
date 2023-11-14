const usernameElement = document.getElementById('username');
const maxLength = 10;
const username = usernameElement.textContent;
let truncatedUsername = '';
let lineLength = 0;

for (let i = 0; i < username.length; i++) {
  if (lineLength === maxLength) {
    // Adiciona uma quebra de linha a cada 10 caracteres
    truncatedUsername += '<br>';
    lineLength = 0;
  }
  
  if (username[i] === ' ') {
    // Se encontrarmos um espaço em branco, também adicionamos uma quebra de linha
    truncatedUsername += '<br>';
    lineLength = 0;
  } else {
    truncatedUsername += username[i];
    lineLength++;
  }
}

usernameElement.innerHTML = truncatedUsername;