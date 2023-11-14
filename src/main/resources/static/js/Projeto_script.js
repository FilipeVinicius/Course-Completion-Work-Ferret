
// Código para fazer o Check-list

// Esta função adiciona uma nova tarefa à lista de tarefas

function addTask() {
	const taskText = document.getElementById('novaTask').value;

	// Verifica se o valor está vazio ou apenas contém espaços em branco
	if (taskText.trim() === '') return;

	const taskList = document.getElementById('task-list');

	// Cria um novo elemento de lista ('li')
	const listItem = document.createElement('li');

	// Define o conteúdo HTML do novo item de lista
	listItem.innerHTML = `<input type="checkbox" class="checadoEstilo" id="checado"  onclick="toggleTask(this)"> ${titulo} ${taskText}`;

	// Adiciona o novo item de lista à lista de tarefas
	taskList.appendChild(listItem);

	// Limpa o campo de entrada de texto
	document.getElementById('novaTask').value = '';


}

// Esta função é chamada quando um checkbox é clicado para alternar o estilo do texto da tarefa
function toggleTask(checkbox) {
	// Obtém o elemento pai (o item de lista 'li') do checkbox
	const listItem = checkbox.parentElement;

	// Se o checkbox estiver marcado, aplica uma linha de texto riscada
	if (checkbox.checked) {
		listItem.style.textDecoration = 'line-through';

	} else {
		// Se o checkbox não estiver marcado, remove a linha de texto riscada
		listItem.style.textDecoration = 'none';

	}
}

// Esta função exclui as tarefas que estão marcadas como concluídas
function deleteTasks() {
	// Obtém a lista de tarefas com id 'task-list'
	const taskList = document.getElementById('task-list');

	// Seleciona todos os checkboxes dentro da lista de tarefas
	const checkboxes = taskList.querySelectorAll('input[type="checkbox"]');

	// Itera sobre cada checkbox
	checkboxes.forEach((checkbox) => {
		if (checkbox.checked) {
			// Obtém o elemento pai (o item de lista 'li') do checkbox e o remove da lista
			const listItem = checkbox.parentElement;
			taskList.removeChild(listItem);
		}
	});
}

// Esta função é chamada quando uma tecla é pressionada no campo de entrada de texto
/*function handleKeyPress(event) {
	// Verifica se a tecla pressionada é 'Enter'
	if (event.key === 'Enter') {
		// Chama a função addTask() para adicionar a tarefa
		addTask();

		// Evita a submissão padrão do formulário (evita recarregar a página)
		event.preventDefault();
	}
}
console.log("Tudo certo");
*/

// Verifica se há um parâmetro na URL que indica uma tarefa excluída
