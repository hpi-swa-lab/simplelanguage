function null() {}

function reverse(list) {
    result = null();

    while (list != null()) {
        result = cons(head(list), result);
        list = tail(list);
    }

    return result;
}

function makeList(numElements) {
    cur = null();

    i = 0;
    while (i < numElements) {
        cur = cons(E, cur);
        i = i + 1;
    }

    return cur;
}

function main() {
    listLength = num(readln());
    reps = num(readln());

    // warumup
    list = makeList(listLength);
    reverse(list);

    i = 0;
    sum = 0;
    while(i < reps) {
        list = makeList(listLength);
        beginTime = nanoTime();
        reversedList = reverse(list);
        endTime = nanoTime();
        result = endTime - beginTime;
        sum = sum + result;
        i = i + 1;
    }
    println(sum / reps);
}
