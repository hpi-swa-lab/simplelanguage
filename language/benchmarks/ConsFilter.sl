function null() {}

function isE(elem) {
    return elem == 0;
}

function isEven(i) {
    return (i / 2) * 2 == i;
}

function filter(list, condition) {
    aux = null();

    while (list != null()) {
        if (condition(head(list))) {
            aux = consWvp(head(list), aux);
        }

        list = tail(list);
    }

    list = null();
    while (aux != null()) {
        list = consWvp(head(aux), list);
        aux = tail(aux);
    }

    return list;
}

function makeList(numElements) {
    cur = null();

    i = 0;
    while (i < numElements) {
        even = isEven(i);
        elem = 1;
        if (even) {
            elem = 0;
        }

        cur = consWvp(elem, cur);
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
    while (i < 3) {
        filter(list, isE);
        i = i + 1;
    }

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
