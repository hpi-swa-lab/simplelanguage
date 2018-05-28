function null() {}

function swap(x) { 
    if (x == E) { 
        return F; 
    } 
    else { 
        return E;
    }
}

function map(list, fn) {
    aux = null();

    while (list != null()) {
        aux = cons(fn(head(list)), aux);
        list = tail(list);
    }

    list = null();
    while (aux != null()) {
        list = cons(head(aux), list);
        aux = tail(aux);
    }
    
    return list;
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

    list = makeList(listLength);

    beginTime = nanoTime();
    mappedList = map(list, swap);
    endTime = nanoTime();
    println(endTime - beginTime);
}
