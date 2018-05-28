function null() {}

function isE(elem) {
    return elem == E;
}

function isEven(i) {
    return (i / 2) * 2 == i;
}

function filter(list, condition) {
    aux = null();

    while (list != null()) {
        if (condition(head(list))) {
            aux = cons(head(list), aux);
        }

        list = tail(list);
    }

    list = null();
    while (aux != null()) {
        list = cons(head(aux), list);
        aux = tail(aux);
    }

    return list;
}

function makeList(numElements) {
    cur = null();

    i = 0;
    while (i < numElements) {
        even = isEven(i);
        elem = F;
        if (even) {
            elem = E;
        }

        cur = cons(elem, cur);
        i = i + 1;
    }

    return cur;
}

function main() {
    listLength = num(readln());

    list = makeList(listLength);

    beginTime = nanoTime();
    filteredList = filter(list, isE);
    endTime = nanoTime();
    println(endTime - beginTime);
}
