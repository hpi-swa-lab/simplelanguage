function null() { }

function makeTree(numElements) {
    if (numElements > 1) {
        return vnew(makeTree(numElements - 1), numElements, makeTree(numElements - 1));
    } else {
        return vnew(null(), 1, null());
    }
}

function traverseLeft(tree) {
    if (get(tree, 0) == null()) {
        return get(tree, 1);
    } else {
        return traverseLeft(get(tree, 0));
    }
}

function main() {
    listLength = num(readln());
    reps = num(readln());

    tree = makeTree(listLength);

    // warmup
    i = 0;
    while (i < 3) {
        traverseLeft(tree);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while(i < reps) {
        gc();
        beginTime = nanoTime();
        result = traverseLeft(tree);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
        sum = sum + result;
    }
    println(sum / reps);
}