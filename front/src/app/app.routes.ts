import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuadministradorComponent } from './components/menuprincipal/menuadministrador/menuadministrador.component';
import { RecetaComponent } from './components/receta/receta.component';

export const routes: Routes = [
    {
        path:'index',
        loadComponent:()=>import('./components/layout/landing/landing.component'),
    },
    {path:'login', component:LoginComponent},
    {path:'menuadministrador', component:MenuadministradorComponent},
    {path:'recetas', component:RecetaComponent},
    {path: '', redirectTo: 'index',pathMatch: 'full'},
    {path: '**',redirectTo: 'index',pathMatch: 'full'},
];
