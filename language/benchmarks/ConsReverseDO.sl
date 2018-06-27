function reverse(list) {
    result = 0;

    while (list != 0) {
        result = cons(list.head, result);
        list = list.tail;
    }

    return result;
}

function makeList(numElements) {
    cur = 0;

    i = 1;
    while (i <= numElements) {
        cur = cons(i, cur);
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
        reverse(list);
        i = i + 1;
    }

    i = 0;
    while(i < reps) {
        beginTime = nanoTime();
        reversedList = reverse(list);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
    }
}
