export type CategoryODAParent = {
  id: number;
  name: string;
  bgColor: string;
  isDelete: boolean;
  childs: CategoryODAChild[];
};

export type CategoryODAChild = {
  id: number;
  name: string;
  isDelete: boolean;
  parentId: number;
  parentName: string;
};

export type CreateCategoryODAChild = {
  parentId: number;
  name: string;
};

export type CreateCategoryODAParent = {
  name: string;
  bgColor: string;
};

export type UpdateCategoryODAParent = {
  name: string;
  bgColor: string;
};

export type UpdateCategoryODAChild = {
  parentId: number;
  name: string;
};
