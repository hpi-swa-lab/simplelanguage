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
    reps = num(readln());

    list = makeList(listLength);

    // warmup
    i = 0;
    while (i < reps) {
        filter(list, isE);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while(i < reps) {
        beginTime = nanoTime();
        filteredList = filter(list, isE);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        sum = sum + result;
        i = i + 1;
    }
    println(sum / reps);
}
