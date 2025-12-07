function validateTransactionForm() {
    let amount = document.getElementById("amount").value;
    let location = document.getElementById("location").value;

    if (amount.trim() === "" || isNaN(amount)) {
        alert("Enter a valid amount");
        return false;
    }

    if (location.trim() === "") {
        alert("Location cannot be empty");
        return false;
    }

    return true;
}
