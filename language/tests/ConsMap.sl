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

function main() {
    list = cons(E, cons(E, cons(F, null())));
    mappedList = map(list, swap);
    println(mappedList);
}
