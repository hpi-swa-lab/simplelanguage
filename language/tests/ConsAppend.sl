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

function main() {
    list1 = cons(1, cons(2, cons(3, null())));
    list2 = cons(4, cons(5, cons(6, null())));

    fullList = append(list1, list2);
    println(fullList);
}
