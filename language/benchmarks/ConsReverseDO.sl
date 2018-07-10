function reverse(list) {
    result = 0;

    while (list != 0) {
        result = consDo(list.head, result);
        list = list.tail;
    }

    return result;
}

function makeList(numElements) {
    cur = 0;

    i = 1;
    while (i <= numElements) {
        cur = consDo(i, cur);
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
        reverse(list);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while(i < reps) {
        gc();
        beginTime = nanoTime();
        reversedList = reverse(list);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
        sum = sum + result;
    }
    println(sum / reps);
}
