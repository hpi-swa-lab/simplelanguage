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
        aux = cons2(fn(head(list)), aux);
        list = tail(list);
    }

    list = null();
    while (aux != null()) {
        list = cons2(head(aux), list);
        aux = tail(aux);
    }
    
    return list;
}

function main() {
    list = cons2(E, cons2(E, cons2(F, null())));
    mappedList = map(list, swap);
    println(mappedList);
}
