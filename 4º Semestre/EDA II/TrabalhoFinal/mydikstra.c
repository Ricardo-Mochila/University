void initialize(aeroportoP)
{
    for(i = aeroportoP, i < SIZE; i++)
    {
        aeroporto.peso = INFINITY;
        aeroporto.parent = NULL;
    }
}

void relax(aeroportop, aeroportoc, peso)
{
    if(aeroportop.peso + peso(aeroportop, aeroportoc) < aeroportoc.peso)
    {
        aeroportoc.peso = aeroportop.peso + peso(aeroportop, aeroportoc);
        aeroportop.parent = aeroportoc;
    }
}

void dikstra(grafo, partida, peso)
{
    initialize(partida);
    caminho = NULL;
    enqueue(partida);

    while(dequeue != NULL)
    {
        u = dequeue();
        caminho = caminho + u.peso;
        for each vertex v in G.adj[u] do
        RELAX(u, v, w) 
    }
}