function main() {
    i = 0;

    while (i < 15) {
        first = vnew(1, 2, 3);
        second = vnew(first, 4, 5);
        println(second);
        i = i + 1;
    }
}