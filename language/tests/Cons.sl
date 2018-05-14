function main() {
    cell1 = cons(1, 2);
    cell2 = cons(3, cell1);
    println(cell1);
    println(cell2);
    cell3 = cons(cell1, 3);
    println(cell3);
    cell4 = cons("hello", cell2);
    println(cell4);
}