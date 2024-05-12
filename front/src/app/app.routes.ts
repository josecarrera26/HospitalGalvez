import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    {
        path:'index',
        loadComponent:()=>import('./components/layout/landing/landing.component'),
    },
    {path:'login', component:LoginComponent},

    {path: '', redirectTo: 'index',pathMatch: 'full'},
    {path: '**',redirectTo: 'index',pathMatch: 'full'},
];
