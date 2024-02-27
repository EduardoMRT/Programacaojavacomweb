function Criar(){
	window.location = "usuarios.xhtml";
}

function verificaSenha(){
	const senha = document.getElementById("senha").value;
	const senhaConfirmada = document.getElementById("senhaConfirmada").value;
	
	if(senha != senhaConfirmada){
		window.alert("As senhas digitadas não são iguais");
		window.location = "entrar.xhtml";
	}
}
