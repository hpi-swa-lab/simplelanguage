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
    if (list == null()) {
        return null();
    }
    
    return cons(fn(head(list)), map(tail(list), fn));
}

function main() {
    list = cons(E, cons(E, cons(F, null())));
    mappedList = map(list, swap);
    println(mappedList);
}
