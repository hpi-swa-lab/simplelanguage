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

    println("makelist");
    list = makeList(listLength);

    println("warmup");
    // warmup
    i = 0;
    while (i < 3) {
        filter(list, isE);
        i = i + 1;
    }

    println("run");
    i = 0;
    sum = 0;
    while(i < reps) {
        gc();
        beginTime = nanoTime();
        filteredList = filter(list, isE);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
        sum = sum + result;
    }
    println(sum / reps);
}
