function null() {}

function reverse(list) {
    result = null();

    while (list != null()) {
        result = cons2(head(list), result);
        list = tail(list);
    }

    return result;
}

function main() {
    cell = cons2(3, cons2(2, cons2(1, null())));
    println(reverse(cell));
}
