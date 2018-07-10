function null() {}

function reverse(list) {
    result = null();

    while (list != null()) {
        result = consWvp(head(list), result);
        list = tail(list);
    }

    return result;
}

function makeList(numElements) {
    cur = null();

    i = 0;
    while (i < numElements) {
        cur = consWvp(1, cur);
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
