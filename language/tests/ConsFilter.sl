function null() {}

function isEven(i) {
    return (i / 2) * 2 == i;
}

function filter(list, condition) {
    aux = null();

    while (list != null()) {
        if (condition(head(list))) {
            aux = cons(head(list), aux);
        }

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
    list = cons(1, cons(1337, cons(42, cons(84, null()))));
    filteredList = filter(list, isEven);
    println(filteredList);
}
