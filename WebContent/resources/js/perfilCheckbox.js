function atualizaCheckbox(element) {
	var valor = element.value;
	if (valor != 1) {
		var row = element.id.split(':')[3];
		var namerow = document.getElementById('form:tabview:cadastroAcao:'
				+ row + ':descricao_acao').innerText.toLocaleLowerCase();
		var acoes = [ 'listar', 'criar', 'editar', 'remover' ];
		for (var x = 0; x < acoes.length; x++) {
			id = namerow + '_' + acoes[x];
			widget = PrimeFaces.widgets[id];
			valor == 0 ? widget.check() : widget.uncheck();
		}
	}

}
