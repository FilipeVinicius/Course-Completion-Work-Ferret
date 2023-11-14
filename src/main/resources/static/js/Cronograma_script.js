// Pegar os seletores do HTML
const currentDate = document.querySelector('.current-date'),
daysTag = document.querySelector('.days'),
prevNext = document.querySelectorAll('.wrapper i');


// Fazer um objeto de data e pegar os anos e os meses
let date = new Date(), 
currYear = date.getFullYear(),
currMonth = date.getMonth();

// Fazer um Array para escrever os meses
let months = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio",
"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"]

// Função que modifica automaticamente os meses do calendário
const renderCalendar = () => {
    let firstDayOfMonth = new Date(currYear, currMonth, 1).getDay(),
    lastDateOfMonth = new Date(currYear, currMonth + 1, 0).getDate(),
    lastDateOfLastMonth = new Date(currYear, currMonth, 0).getDate(),
    liTag = "";

    for (let index = firstDayOfMonth; index > 0; index--) {
      liTag += `<li class="inactive">${lastDateOfLastMonth - index + 1}</li>`;    
    }

    for (let index = 1; index <= lastDateOfMonth; index++) {
      liTag += `<li class="active" data-bs-toggle="modal" data-bs-target="#calendarModal">${index}</li>`;
    }

    currentDate.innerText = `${months[currMonth]} ${currYear}`
    daysTag.innerHTML = liTag;
}
renderCalendar();

// Adicionar evento de click nos ícones para passar os meses
prevNext.forEach(icon => {
    icon.addEventListener("click", () => {
      currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;
       // ? = condição, valor retornado se verdadeiro : valor retornado se falso

      if (currMonth < 0 || currMonth > 11) {
        date = new Date(currYear, currMonth);
        currYear = date.getFullYear();
        currMonth = date.getMonth();
      } else {
        date = new Date();
      }
      renderCalendar(); 
    });
});


// Separado
document.addEventListener('DOMContentLoaded', function () {
    const currentDate = document.querySelector('.current-date');
    const daysTag = document.querySelector('.days');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const criarEventoBtn = document.getElementById('concluido');
    const eventoNome = document.querySelector('.eventNameTextArea');
    const eventoDataInicio = document.querySelector('.dataInicio');
    const eventoDataFim = document.querySelector('.dataFim');

    let date = new Date();
    let currYear = date.getFullYear();
    let currMonth = date.getMonth();

    const months = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio",
        "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

    const renderCalendar = () => {
        let firstDayOfMonth = new Date(currYear, currMonth, 1).getDay();
        let lastDateOfMonth = new Date(currYear, currMonth + 1, 0).getDate();
        let lastDateOfLastMonth = new Date(currYear, currMonth, 0).getDate();
        let liTag = "";

        for (let index = firstDayOfMonth; index > 0; index--) {
            liTag += `<li class="inactive">${lastDateOfLastMonth - index + 1}</li>`;
        }

        for (let index = 1; index <= lastDateOfMonth; index++) {
            liTag += `<li class="active">${index}</li>`;
        }

        currentDate.innerText = `${months[currMonth]} ${currYear}`;
        daysTag.innerHTML = liTag;

        // Adicione um evento de clique para exibir detalhes do evento no modal quando um evento for clicado
        const eventos = document.querySelectorAll('.evento');
        eventos.forEach(evento => {
            evento.addEventListener('click', function () {
                modalNomeEvento.textContent = evento.querySelector('strong').textContent;
                modalDataInicio.value = evento.getAttribute('data-inicio');
                modalDataFim.value = evento.getAttribute('data-fim');
                $('#calendarModal').modal('show');
            });
        });
    };

    renderCalendar();

    prevButton.addEventListener("click", () => {
        currMonth = currMonth - 1;
        if (currMonth < 0) {
            currYear = currYear - 1;
            currMonth = 11;
        }
        renderCalendar();
    });

    nextButton.addEventListener("click", () => {
        currMonth = currMonth + 1;
        if (currMonth > 11) {
            currYear = currYear + 1;
            currMonth = 0;
        }
        renderCalendar();
    });

    criarEventoBtn.addEventListener('click', () => {
        const nome = eventoNome.value.trim();
        const dataInicio = eventoDataInicio.value;
        const dataFim = eventoDataFim.value;

        if (nome !== '' && dataInicio !== '' && dataFim !== '') {
            const selectedDay = document.querySelector('.active'); // Modifiquei para selecionar o dia ativo
            if (selectedDay) {
                const novoEvento = document.createElement('div');
                novoEvento.className = 'evento';
                novoEvento.innerHTML = `<strong>${nome}</strong><br>Data de início: ${dataInicio}<br>Data de término: ${dataFim}`;
                novoEvento.setAttribute('data-inicio', dataInicio);
                novoEvento.setAttribute('data-fim', dataFim);
                selectedDay.appendChild(novoEvento);
                // Limpe os campos do formulário
                eventoNome.value = '';
                eventoDataInicio.value = '';
                eventoDataFim.value = '';
                $('#calendarModal').modal('hide');
            } else {
                alert('Selecione um dia no calendário antes de adicionar um evento.');
            }
        } else {
            alert('Preencha todos os campos do evento.');
        }
        
        console.log("Funcionou");
    });
});