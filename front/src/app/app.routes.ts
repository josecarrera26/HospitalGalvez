import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuadministradorComponent } from './components/menuprincipal/menuadministrador/menuadministrador.component';
import { FacturaComponent } from './components/factura/factura.component';


export const routes: Routes = [
    {
        path:'index',
        loadComponent:()=>import('./components/layout/landing/landing.component'),
    },
    {path:'login', component:LoginComponent},
    {path:'menuadministrador', component:MenuadministradorComponent},
    {path:'factura', component:FacturaComponent},

    {path: '', redirectTo: 'index',pathMatch: 'full'},
    {path: '**',redirectTo: 'index',pathMatch: 'full'},
];
