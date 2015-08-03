/*
Program to define Identify articulation vertices in a graph.

An articulation vertex is the weakest point in the graph.

There is a single point of failure—a single vertex whose deletion disconnects
a connected component of the graph. Such a vertex is called an articulation vertex
or cut-node. Any graph that contains an articulation vertex is inherently fragile,
because deleting that single vertex causes a loss of connectivity between other nodes.

In general, the connectivity of a graph is the smallest number of vertices whose deletion
will disconnect the graph. The connectivity is one if the graph has an articulation vertex.
More robust graphs without such a vertex are said to be biconnected.

Finding articulation vertices requires maintaining the extent to which back edges
link chunks of the DFS tree back to ancestor nodes. Let reachable ancestor[v] denote
the earliest reachable ancestor of vertex v, meaning the oldest ancestor of v that
we can reach by a combination of tree edges and back edges.

The key issue is determining how the reachability relation impacts whether vertex v
is an articulation vertex. There are three cases

1. Root cut-nodes
2. Bridge cut-nodes
2. Parent cut-nodes

The routine process_vertex_late(int v) systematically evaluates each of the three conditions
as we back up from the vertex after traversing all outgoing edges.

Author : Risman Adnan, SRIN

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

int reachable_ancestor[MAXV + 1]; /* earliest reachable ancestor of v */
int tree_out_degree[MAXV + 1];    /* DFS tree outdegree of v */


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

	for (i = 0; i< g->nvertices; i++) {
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
	return TREE;
}

void process_vertex_early(int v)
{
	reachable_ancestor[v] = v;
}

void process_vertex_late(int v)
{
	bool root;      /* is a given vertex the root of the DFS tree? */
	int time_v;     /* earliest reachable time for v */
	int time_parent;    /* earliest reachable time for parent[v] */

	if (parent[v] < 1) {    /* test if v is the root */
		if (tree_out_degree[v] > 1)cout << "root articulation vertex: " << v << endl;
		return;
	}

	root = (parent[parent[v]] < 1);     /* test if parent[v] is root */
	if ((reachable_ancestor[v] == parent[v]) && (!root))
		cout << "parent articulation vertex: " << parent[v] << endl;

	if (reachable_ancestor[v] == v) {
		cout << "bridge articulation vertex: " << parent[v] << endl;
		if (tree_out_degree[v] > 0)     /* test if v is not a leaf */
			cout << "bridge articulation vertex: " << v << endl;
	}

	time_v = entry_time[reachable_ancestor[v]];
	time_parent = entry_time[reachable_ancestor[parent[v]]];

	if (time_v < time_parent)
		reachable_ancestor[parent[v]] = reachable_ancestor[v];
}

void process_edge(int x, int y)
{
	int classification;     /* edge class */

	classification = edge_classification(x, y);

	/*printf("(%d,%d) class %d tree_out_degree[%d]=%d\n", x,y,class,x,tree_out_degree[x]);*/
	if (classification == TREE)
		tree_out_degree[x] = tree_out_degree[x] + 1;

	if ((classification == BACK) && (parent[x] != y)) {
		if (entry_time[y] < entry_time[reachable_ancestor[x]])
			reachable_ancestor[x] = y;
	}
}


void dfs(graph *g, int v)
{
	edgenode *p;            /* temporary pointer */
	int y;              /* successor vertex */

	if (finished) return;       /* allow for search termination */

	discovered[v] = TRUE;
	timeint = timeint + 1;
	entry_time[v] = timeint;
	/*printf("entered vertex %d at time %d\n",v, entry_time[v]);*/

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
	/*printf("exit vertex %d at time %d\n",v, exit_time[v]);*/

	processed[v] = TRUE;
}



void articulation_vertices(graph *g)
{
	int i;              /* counter */
	for (i = 0; i < (g->nvertices); i++)tree_out_degree[i] = 0;
	initialize_search(g);

	for (i = 1; i <= (g->nvertices); i++)
		if (discovered[i] == FALSE) dfs(g, i);
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
	articulation_vertices(&g);
	getchar();
}