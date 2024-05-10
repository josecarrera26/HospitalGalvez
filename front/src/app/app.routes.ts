import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path:'index',
        loadComponent:()=>import('./components/layout/landing/landing.component'),
    },

    {path: '', redirectTo: 'index',pathMatch: 'full'},
    {path: '**',redirectTo: 'index',pathMatch: 'full'},
];
