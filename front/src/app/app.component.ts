import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/layout/header/header.component';
import { initFlowbite } from 'flowbite';
import { FooterComponent } from './components/layout/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,HeaderComponent,FooterComponent,LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'front';

  constructor(private activerouter: ActivatedRoute, private router: Router) {
    //this.dataSource.data = TREE_DATA;

  }

  ngOnInit(): void {
   initFlowbite();

   //this.factura();
  }

  factura(){
    this.router.navigate(['/factura']);
  
  }
}
