function null() {}

function append(list1, list2) {
    aux = null();

    while (list1 != null()) {
        aux = consWvp(head(list1), aux);
        list1 = tail(list1);
    }

    while (aux != null()) {
        list2 = consWvp(head(aux), list2);
        aux = tail(aux);
    }

    return list2;
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
    listLength = num(readln()) / 2;
    reps = num(readln());

    list1 = makeList(listLength);
    list2 = makeList(listLength);

    // warmup
    i = 0;
    while (i < 3) {
        append(list1, list2);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while(i < reps) {
        gc();
        beginTime = nanoTime();
        list3 = append(list1, list2);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
        sum = sum + result;
    }
    println(sum / reps);
}