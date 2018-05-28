function null() {}

function reverse(list) {
    result = null();

    while (list != null()) {
        result = cons(head(list), result);
        list = tail(list);
    }

    return result;
}

function main() {
    cell = cons(3, cons(2, cons(1, null())));
    println(reverse(cell));
}
