/*
Simple DFS traversal demo in a directed / undirected graph.
The exact behavior of dfs depends upon the functions and stack data structure:
1. process vertex early()
2. process vertex late()
3. process edge()


This implementation of dfs maintains a notion of traversal time for each vertex.
Time clock ticks each time we enter or exit any vertex.
This DFS has a neat recursive implementation, which eliminates use of stack.

The time intervals have interesting and useful properties with respect to DFS:
1. Who is an ancestor? Time interval of y must be properly nested within ancestor x.
2. How many descendants? Half time difference denotes the number of descendents of v.

When traversing undirected graphs, every edge is either in the depth-first search tree or
a back edge to an ancestor in the tree. TREE, BACK, FORWARD, CROSS.

Author : Risman Adnan

*/


#include<cstdio>
#include<iostream>

using namespace std;

#define MAXV        100     /* maximum number of vertices */

/*  DFS edge types          */
#define TREE        0       /* tree edge */
#define BACK        1       /* back edge */
#define CROSS       2       /* cross edge */
#define FORWARD     3       /* forward edge */

#define TRUE    1
#define FALSE   0

bool processed[MAXV + 1];     /* which vertices have been processed */
bool discovered[MAXV + 1];    /* which vertices have been found */
int parent[MAXV + 1];         /* discovery relation */

int entry_time[MAXV + 1];     /* time of vertex entry */
int exit_time[MAXV + 1];      /* time of vertex exit */
int timeint;                   /* current event time */

bool finished = FALSE;      /* if true, cut off search immediately */


typedef struct edgenode {
	int y;              /* adjancency info */
	int weight;         /* edge weight, if any */
	edgenode *next;     /* next edge in list */
} edgenode;

typedef struct graph {
	edgenode *edges[MAXV + 1];    /* adjacency info */
	int degree[MAXV + 1];         /* outdegree of each vertex */
	int nvertices;              /* number of vertices in the graph */
	int nedges;                 /* number of edges in the graph */
	int directed;               /* is the graph directed? */
} graph;



void initialize_graph(graph *g, bool directed)
{
	int i;              /* counter */

	g->nvertices = 0;
	g->nedges = 0;
	g->directed = directed;

	for (i = 0; i < MAXV; i++) g->degree[i] = 0;
	for (i = 0; i < MAXV; i++) g->edges[i] = NULL;
}

void insert_edge(graph *g, int x, int y, bool directed)
{
	edgenode *p;            /* temporary pointer */

	p = new edgenode(); /* allocate storage for edgenode */

	p->weight = NULL;
	p->y = y;
	p->next = g->edges[x];

	g->edges[x] = p;        /* insert at head of list */

	g->degree[x] ++;

	if (directed == FALSE)
		insert_edge(g, y, x, TRUE);
	else
		g->nedges++;
}

void read_graph(graph *g, bool directed)
{
	int i;              /* counter */
	int m;              /* number of edges */
	int x, y;           /* vertices in edge (x,y) */

	initialize_graph(g, directed);
	cin >> g->nvertices;
	cin >> m;

	for (i = 0; i < m; i++) {
		cin >> x >> y;
		insert_edge(g, x, y, directed);
	}
}


void delete_edge(graph *g, int x, int y, bool directed)
{
	edgenode *p, *p_back;       /* temporary pointers */

	p = g->edges[x];
	p_back = NULL;

	while (p != NULL)
		if (p->y == y) {
			g->degree[x] --;
			if (p_back != NULL)
				p_back->next = p->next;
			else
				g->edges[x] = p->next;

			delete p;

			if (directed == FALSE)
				delete_edge(g, y, x, TRUE);
			else
				g->nedges--;

			return;
		}
		else
			p = p->next;

	cout << "Warning: deletion(" << x << "," << y << ") not found in graph" << endl;
}

void print_graph(graph *g)
{
	int i;              /* counter */
	edgenode *p;            /* temporary pointer */

	for (i = 0; i < g->nvertices; i++) {
		cout << i << ":";
		p = g->edges[i];
		while (p != NULL) {
			cout << " " << p->y;
			p = p->next;
		}
		cout << endl;
	}
}

void initialize_search(graph *g)
{
	int i;                          /* counter */

	timeint = 0;

	for (i = 0; i < g->nvertices; i++) {
		processed[i] = discovered[i] = FALSE;
		parent[i] = -1;
	}
}

int edge_classification(int x, int y)
{
	if (parent[y] == x) return(TREE);
	if (discovered[y] && !processed[y]) return(BACK);
	if (processed[y] && (entry_time[y]>entry_time[x])) return(FORWARD);
	if (processed[y] && (entry_time[y]<entry_time[x])) return(CROSS);

	cout << "Warning: self loop (" << x << "," << y << ")" << endl;
	return TREE; // I am not sure about this ya
}

void process_vertex_early(int v)
{
	timeint = timeint + 1;
	entry_time[v] = timeint;
	cout << "entered vertex " << v << " at time " << entry_time[v] << endl;
}

void process_vertex_late(int v)
{
	timeint = timeint + 1;
	exit_time[v] = timeint;
	cout << "exit vertex " << v << " at time " << entry_time[v] << endl;
}

void process_edge(int x, int y)
{
	int classify;       /* edge class */

	classify = edge_classification(x, y);

	if (classify == BACK) cout << "back edge (" << x << "," << y << ")" << endl;
	else if (classify == TREE) cout << "tree edge (" << x << "," << y << ")" << endl;
	else if (classify == FORWARD) cout << "forward edge (" << x << "," << y << ")" << endl;
	else if (classify == CROSS) cout << "cross edge (" << x << "," << y << ")" << endl;
	else cout << "edge (" << x << "," << y << ") not in valid class" << classify << endl;
}


void dfs(graph *g, int v)
{
	edgenode *p;            /* temporary pointer */
	int y;              /* successor vertex */

	if (finished) return;       /* allow for search termination */

	discovered[v] = TRUE;
	timeint = timeint + 1;
	entry_time[v] = timeint;
	cout << "entered vertex " << v << " at time " << entry_time[v] << endl;

	process_vertex_early(v);

	p = g->edges[v];
	while (p != NULL) {
		y = p->y;
		if (discovered[y] == FALSE) {
			parent[y] = v;
			process_edge(v, y);
			dfs(g, y);
		}
		else if ((!processed[y]) || (g->directed))
			process_edge(v, y);

		if (finished) return;

		p = p->next;
	}

	process_vertex_late(v);

	timeint = timeint + 1;
	exit_time[v] = timeint;
	cout << "exit vertex " << v << " at time " << entry_time[v] << endl;
	processed[v] = TRUE;
}


void find_path(int start, int end, int parents[])
{
	if ((start == end) || (end == -1)){
		cout << endl;
		cout << start;
	}
	else {
		find_path(start, parents[end], parents);
		cout << " " << end;
	}
}


int main()
{
	graph g;
	initialize_graph(&g, FALSE);
	g.nvertices = 4;
	insert_edge(&g, 0, 1, FALSE);
	insert_edge(&g, 0, 2, FALSE);
	insert_edge(&g, 1, 2, FALSE);
	insert_edge(&g, 2, 0, FALSE);
	insert_edge(&g, 2, 3, FALSE);
	insert_edge(&g, 3, 3, FALSE);
	print_graph(&g);

	initialize_search(&g);
	dfs(&g, 2);

	for (int i = 1; i <= g.nvertices; i++) find_path(1, i, parent);

}