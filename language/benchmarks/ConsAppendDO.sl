function append(list1, list2) {
    aux = 0;

    while (list1 != 0) {
        aux = consDo(list1.head, aux);
        list1 = list1.tail;
    }

    while (aux != 0) {
        list2 = consDo(aux.head, list2);
        aux = aux.tail;
    }

    return list2;
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