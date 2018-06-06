function null() {}

function append(list1, list2) {
    aux = null();

    while (list1 != null()) {
        aux = cons(head(list1), aux);
        list1 = tail(list1);
    }

    while (aux != null()) {
        list2 = cons(head(aux), list2);
        aux = tail(aux);
    }

    return list2;
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

    list1 = makeList(listLength);
    list2 = makeList(listLength);

    // warmup
    i = 0;
    while (i < reps) {
        append(list1, list2);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while(i < reps) {
        beginTime = nanoTime();
        list3 = append(list1, list2);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        sum = sum + result;
        i = i + 1;
    }
    println(sum / reps);
}
