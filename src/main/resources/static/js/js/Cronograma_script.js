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


