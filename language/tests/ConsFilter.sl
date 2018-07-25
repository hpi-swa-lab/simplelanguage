function null() {}

function isEven(i) {
    return (i / 2) * 2 == i;
}

function filter(list, condition) {
    aux = null();

    while (list != null()) {
        if (condition(head(list))) {
            aux = cons2(head(list), aux);
        }

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
    list = cons2(1, cons2(1337, cons2(42, cons2(84, null()))));
    filteredList = filter(list, isEven);
    println(filteredList);
}
