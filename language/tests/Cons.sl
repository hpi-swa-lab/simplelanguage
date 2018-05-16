function null() {}

function reverseAcc(l, acc) {
    if (l == null()) {
        return acc;
    } else {
        return reveseAcc(tail(l), cons(head(l), acc));
    }
}

function reverse(l) {
    return reverseAcc(l, null());
}

function main() {
    cell1 = cons(1, null());
    //println(head(cell1));
    //println(tail(cell1));
    //println(cell1);
    cell2 = cons(3, cell1);
    println(cell2);
    cell3 = cons(cell1, 3);
    println(cell3);
    //println(reverse(cell3));
    //cell4 = cons("hello", cell2);
    //println(cell4);
}