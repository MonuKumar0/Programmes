#include <bits/stdc++.h>
using namespace std;
struct Node
{
    int data;
    struct Node* left;
    struct Node* right;
};
struct Node* newNode(int data)
{
  struct Node* node = new Node;
  node->data = data;
  node->left = NULL;
  node->right = NULL;
  return(node);
}
int diameter(struct Node * tree);
int main()
{
  int t;
  scanf("%d",&t);
  while(t--)
  {
     map<int, Node*> m;
     int n;
     scanf("%d",&n);
     struct Node *root = NULL;
     struct Node *child;
     while (n--)
     {
        Node *parent;
        char lr;
        int n1, n2;
        scanf("%d %d %c", &n1, &n2, &lr);
        if (m.find(n1) == m.end())
        {
           parent = newNode(n1);
           m[n1] = parent;
           if (root == NULL)
             root = parent;
        }
        else
           parent = m[n1];
        child = newNode(n2);
        if (lr == 'L')
          parent->left = child;
        else
          parent->right = child;
        m[n2]  = child;
     }
     cout << diameter(root) << endl;
  }
  return 0;
}

 struct Node
 {
     int data;
     Node* left, *right;
}; 
int maxchildren[10001];
int c=0;
int fillChildren(Node* node)
{
    if((node->left==NULL)&&(node->right==NULL)){
        maxchildren[node->data] = 1;
    }
    else if(node->left==NULL){
        maxchildren[node->data]=1+ fillChildren(node->right);
    }
    else if(node->right==NULL){
        maxchildren[node->data]=1+fillChildren(node->left);
    }
    else{
        maxchildren[node->data]= 1+max(fillChildren(node->left),fillChildren(node->right));
    }
    return maxchildren[node->data];
}
int mydiameter(Node* node)
{
    if(c==0){
        c=1;
        for(int i=0;i<10001;i++)
        {
            maxchildren[i]=0;
        }
        fillChildren(node);
    }
    if(node==NULL) return 0;
    if((node->left==NULL)&&(node->right==NULL)){
        return 1;
    }
    else if((node->right==NULL)){
        return max(1+maxchildren[node->left->data],mydiameter(node->left));
    }
    else if((node->left==NULL)){
        return max(1+maxchildren[node->right->data],mydiameter(node->right));
    }
    return max(1+maxchildren[node->left->data]+maxchildren[node->right->data],max(mydiameter(node->left),mydiameter(node->right)));
   
}
int diameter(Node* node){
    c=0;
    return mydiameter(node);
}
int max(int a,int b){
    return (a>b)?a:b;
}
