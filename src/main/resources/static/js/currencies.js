let currencyMap;
$(document).ready(async function () {
    await getCurrencyNames();
    setCalendarDate();
});

function setCalendarDate() {
    let calendar = document.querySelector('#calendar');
    let today = new Date();
    calendar.value = today.toISOString().substr(0, 10);
}

async function getCurrencyNames() {

    let request = await fetch("api/currencies/getAllCurrenciesNames", {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })
    let response = await request.json();
    currencyMap = new Map(Object.entries(response));

    let select_input = document.querySelector('#selectInput');
    let select_output = document.querySelector('#selectOutput');
    for (let key of currencyMap.keys()) {
        let opt_in = document.createElement('option');
        opt_in.value = key;
        opt_in.innerHTML = key;

        let opt_out = document.createElement('option');
        opt_out.value = key;
        opt_out.innerHTML = key;

        select_input.appendChild(opt_in);
        select_output.appendChild(opt_out);
    }
}

$('#calculate').click(async function () {
        let dataToRequest = $('#converter_form').serialize();
        let selectInputValue = document.querySelector('select[name="selectInput"]').value;
        let selectOutputValue = document.querySelector('select[name="selectOutput"]').value;
        let data = {
            date:  document.querySelector('input[name="date"]').value,
            amountInput: document.querySelector('input[name="amountInput"]').value,
            selectInput: currencyMap.get(selectInputValue),
            selectOutput: currencyMap.get(selectOutputValue),
        }

        let response = await fetch("/api/calculating/calculate", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        let outPutAmount = await response.json();
        document.querySelector('input[id="amountOutput"]').value = outPutAmount;
        getHistoryTable();
    }
)

