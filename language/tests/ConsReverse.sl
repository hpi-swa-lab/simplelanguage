function null() {}

function reverseAcc(l, acc) {
    if (l == null()) {
        return acc;
    } else {
        return reverseAcc(tail(l), cons(head(l), acc));
    }
}

function reverse(l) {
    return reverseAcc(l, null());
}

function main() {
    cell = cons(3, cons(2, cons(1, null())));
    println(reverse(cell));
}
