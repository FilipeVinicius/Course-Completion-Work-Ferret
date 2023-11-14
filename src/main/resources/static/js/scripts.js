/* Pré Visualização da Imagem Escolhida */
function preViewImg() {
	var imgPreView = new FileReader();
	imgPreView.readAsDataURL(document.getElementById("uploadImage").files[0]);

	imgPreView.onload = function(imgPreViewEvent) {
		document.getElementById("preView").src = imgPreViewEvent.target.result;
	}
}

function validate() {
	var novaSenha= document.getElementById("inputNovaSenha");
	var confirmaNovaSenha= document.getElementById("inputConfirmaNovaSenha");
	
	if(novaSenha.value == confirmaNovaSenha.value) {
		document.getElementById("serverMessage").innerText = "";
		document.getElementById("alterarSenha").disabled = false;
		
		return true;
	} else {
		document.getElementById("inputConfirmaNovaSenha").value = "";
		document.getElementById("serverMessage").innerText = "As senhas não conferem!!!";
		document.getElementById("alterarSenha").disabled = true;
		
		return false;
	}
}
