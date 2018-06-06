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

    // warmup
    makeList(listLength);

    list1 = makeList(listLength);
    list2 = makeList(listLength);

    i = 0;
    sum = 0;
    while(i < reps) {
        beginTime = nanoTime();
        list3 = append(list1, list2);
        endTime = nanoTime();
        result = endTime - beginTime;
        sum = sum + result;
        i = i + 1;
    }
    println(sum / reps);
}
