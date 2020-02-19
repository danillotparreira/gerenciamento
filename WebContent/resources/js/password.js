function buttonShowHide() {
	var field = $('#form\\:senha');
	var button = document.getElementById("showHide").getElementsByTagName('i')[0];
	var verificacao = field.attr('type') == 'password';
	if (verificacao) {
		button.className = 'pi pi-eye-slash';
		field.attr('type', 'text');
	} else {
		button.className = 'pi pi-eye';
		field.attr('type', 'password');
	}
}