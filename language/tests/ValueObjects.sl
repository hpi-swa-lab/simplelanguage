function main() {
    i = 0;

    while (i < 15) {
        first = vnew(1, 2, 3);
        second = vnew(first, 4, 5);
        third = vnew(second, 6, 7);
        println(third);
        i = i + 1;
    }
}