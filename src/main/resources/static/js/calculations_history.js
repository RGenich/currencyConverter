$(document).ready(async function () {
    await getHistoryTable();
});

async function getHistoryTable() {
    let response = await fetch("api/calculating/getHistory", {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })
    let answer = await response.json();

    $("#history_table tbody tr").remove();

    for (let i = 0; i < answer.length; i++){
        $("#history_table tbody").prepend(
            "<tr>" +
                    "<td>" + answer[i].selectInput + "</td>" +
                    "<td>" + answer[i].selectOutput + "</td>" +
                    "<td>" + answer[i].amountInput + "</td>" +
                    "<td>" + answer[i].amountOutput + "</td>" +
                    "<td>" + answer[i].date + "</td>" +
                    "<td>" + answer[i].requestDate + "</td>" +
            "</tr>"
        );
    }
}
