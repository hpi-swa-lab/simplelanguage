function isE(elem) {
    return elem == 0;
}

function isEven(i) {
    return (i / 2) * 2 == i;
}

function filter(list, condition) {
    aux = 0;

    while (list != 0) {
        if (condition(list.head)) {
            aux = consDo(list.head, aux);
        }

        list = list.tail;
    }

    list = 0;
    while (aux != 0) {
        list = consDo(aux.head, list);
        aux = aux.tail;
    }

    return list;
}

function makeList(numElements) {
    cur = 0;

    i = 1;
    while (i <= numElements) {
        even = isEven(i);
        elem = 1;
        if (even) {
            elem = 0;
        }

        cur = consDo(elem, cur);
        i = i + 1;
    }

    return cur;
}

function main() {
    listLength = num(readln());
    reps = num(readln());

    list = makeList(listLength);

    // warmup
    warmupReps = reps;
    if (reps > 100) {
        warmupReps = 100;
    }
    i = 0;
    while (i < warmupReps) {
        filter(list, isE);
        i = i + 1;
    }

    i = 0;
    while(i < reps) {
        beginTime = nanoTime();
        filteredList = filter(list, isE);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
    }
}
