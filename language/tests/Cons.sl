function null() {}

function main() {
    cell1 = cons2(1, null());
    println(head(cell1));
    println(tail(cell1));
    println(cell1);
    cell2 = cons2(3, cell1);
    println(cell2);
    cell3 = cons2(cell1, 3);
    println(cell3);
}