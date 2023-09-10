
EN

Welcome to “Introduction to Containers.”
After that, you will be able to


identify the traditional computing issues for software development,
define a container and describe its characteristics, and list container benefits and challenges, and popular container vendors.

Cloud-native is the newest application development approach for building scalable, dynamic, hybrid cloud-friendly software.
Container technology is a powerful part of that approach.
Let’s check out the analogy of a shipping container.

The modern shipping industry standardized a set of container sizes,
so no matter what item is shipped, the container size remains the same.

Standardization significantly improves shipping efficiency.

Logistics staff select container transport options such as ships, planes, trains, and trucks, based on the container’s size and the client’s delivery needs.
Digital container technology is similar. Containers solve the problem of making software portable so that applications can run on multiple platforms.

A container, powered by the containerization engine, is a standard unit of software that encapsulates the application code,
runtime, system tools, system libraries, and settings necessary for programmers to build, ship, and run applications efficiently.

Operations and underlying infrastructure issues are no longer blockers.

You can quickly move applications from your laptop to a testing environment, from a staging environment to a production environment,
from a physical machine to a virtual machine, or a private cloud or public cloud, and always know that your application will work correctly.

A container can be small, just tens of megabytes, and developers can almost instantly start containerized applications.

With these capabilities, containers serve as the foundation for today’s development and deployment solutions standards.
Let’s examine some of the development and deployment challenges organizations encounter with traditional computing environments.

In traditional environments, developers can’t isolate the app and allocate or designate specific storage and memory resources for apps on physical servers. 
Servers are often underutilized or overutilized, leading to poor utilization and a poor return on investment. 
Traditional deployments require comprehensive provisioning resources and expensive maintenance costs. 
The limits of physical servers can constrain application performance during peak workloads. 
Applications are not portable across multiple environments and operating systems. 
Implementing hardware for resiliency is often time-consuming, complex and expensive. 
Traditional on premises IT environments have limited scalability Finally, automation is challenging when distributing software to multiple platforms and resources using traditional environments. 
Containers enable organizations to overcome these challenges. 
Container engines virtualize the operating system and are responsible for running containers. 
Platform-independent containers are lightweight, fast, isolated, portable, and secure and often require less memory space. 
Binaries, libraries, and other entities within the container enable apps to run, and one machine can host multiple containers. 
Containers help programmers quickly deploy code into applications Containers are platform-independent and can run on the cloud, desktop, and on-premises Containers being operating system-independent, run on Windows, Linux, or Mac OS.
Containers are also programming language and IDE independent—whether you are running Python, Node, Java, or other languages. 
Containers enable organizations to: Quickly create applications using automation. 
Lower deployment time and costs. Improve resource utilization, including CPU and memory. Port across different environments, and support next-gen applications, including microservices. 
Using containerization is not without its challenges. Server security can become an issue if its operating system is affected. 
Developers can become overwhelmed when managing thousands of containers. 
Converting monolithic legacy applications can be a complex process, and Developers can experience difficulty right-sizing containers for specific scenarios.
Next, let’s learn about some of the more popular container vendors.
Docker is a robust platform and the most popular container platform today.
Podman is a daemon-less container engine that is more secure than Docker.
Developers often prefer LXC for data-intensive applications and operations.
And, Vagrant offers the highest levels of isolation on the running physical machine.
In this video, you learned that organizations are moving to containers to overcome challenges around isolation, utilization, provisioning, performance, and more.
A container is a standard unit of software that encapsulates everything needed to build, ship, and run applications.
Containers are operating system, programming language, and platform-independent.
They lower deployment time and costs, improve utilization, automate processes, and support next-gen applications (microservices). 
Developers may find that management, legacy project migration, and right-sizing are significant challenges.
And finally, major container vendors include Docker, Podman, LXC, and Vagrant.

PT

*Sobre Containes

Depois disto você poderá identificar os problemas de computação tradicionais para o desenvolvimento de software,
definir um contêiner e descrever suas características, além de listar os benefícios e os desafios dos containers e os fornecedores populares de contêineres.

*Transformação digital e containers

O Cloud-native é a mais nova abordagem de desenvolvimento de aplicativos para criar software escalável, dinâmico e compatível com a nuvem híbrida.
A tecnologia de contêineres é uma parte poderosa dessa abordagem.
Vamos conferir a analogia de um contêiner de transporte.

A indústria naval moderna padronizou um conjunto de tamanhos de contêineres,
portanto, não importa qual item seja enviado, o tamanho do contêiner permanece o mesmo.

A padronização melhora significativamente a eficiência do transporte.

A equipe de logística seleciona opções de transporte de contêineres, como navios, aviões, trens e caminhões,
com base no tamanho do contêiner e nas necessidades de entrega do cliente.

A tecnologia de contêineres digitais é semelhante.
Os contêineres resolvem o problema de tornar o software portátil para que os aplicativos possam ser executados em várias plataformas.

*Definição de Container

Um contêiner, alimentado pelo mecanismo de conteinerização, é uma unidade padrão de software que encapsula o código do aplicativo,
o tempo de execução, as ferramentas do sistema, as bibliotecas do sistema e as configurações necessárias para que os programadores criem, enviem e executem aplicativos com eficiência.

As operações e os problemas de infraestrutura subjacentes não são mais bloqueadores.

Você pode mover rapidamente os aplicativos do seu laptop para um ambiente de teste, de um ambiente de preparação para um ambiente de produção,
de uma máquina física para uma máquina virtual, ou de uma nuvem privada ou pública, e sempre saber que seu aplicativo funcionará corretamente.

Um contêiner pode ser pequeno, com apenas dezenas de megabytes, e os desenvolvedores podem iniciar aplicativos em contêineres quase instantaneamente.

Com esses recursos, os contêineres servem como base para os padrões atuais de soluções de desenvolvimento e implantação.

*Porque utilizar container

Vamos examinar alguns dos desafios de desenvolvimento e implantação que as organizações enfrentam em ambientes de computação tradicionais.

Em ambientes tradicionais, os desenvolvedores não podem isolar o aplicativo e alocar ou designar recursos específicos de armazenamento e memória para aplicativos em servidores físicos.

Os servidores geralmente são subutilizados ou sobreutilizados, levando a uma utilização inadequada e a um baixo retorno sobre o investimento.

As implantações tradicionais exigem recursos abrangentes de provisionamento e altos custos de manutenção.
Os limites dos servidores físicos podem restringir o desempenho dos aplicativos durante picos de carga de trabalho.
Os aplicativos não são portáteis em vários ambientes e sistemas operacionais.
A implementação de hardware para resiliência geralmente é demorada, complexa e cara.
Ambientes de TI locais tradicionais têm escalabilidade limitada.
Finalmente, a automação é um desafio ao distribuir software para várias plataformas e recursos usando ambientes tradicionais.

Os contêineres permitem que as organizações superem esses desafios.

Os mecanismos de contêiner virtualizam o sistema operacional e são responsáveis pela execução dos contêineres.
Os contêineres independentes de plataforma são leves, rápidos, isolados, portáteis e seguros e geralmente exigem menos espaço na memória.

Binários, bibliotecas e outras entidades dentro do contêiner permitem que os aplicativos sejam executados, e uma máquina pode hospedar vários contêineres.

Os contêineres ajudam os programadores a implantar rapidamente o código em aplicativos.

*Caracteristicas dos containers

Os contêineres são independentes da plataforma e podem ser executados na nuvem, no desktop e no local.
Os contêineres, independentes do sistema operacional, são executados no Windows, Linux ou Mac OS.
Os contêineres também são independentes da linguagem de programação e do IDE, independentemente de você estar executando Python, Node, Java ou outras linguagens.


*Benefícios dos containeres

Os contêineres permitem que as organizações: criem aplicativos rapidamente usando automação e reduza o tempo e os custos de implantação.
Melhore a utilização de recursos, incluindo CPU e memória.
Faça a portabilidade em diferentes ambientes e ofereça suporte a aplicativos de próxima geração, incluindo microsserviços.

*Desafios dos Containers

Usar a conteinerização tem seus desafios.

A segurança do servidor pode se tornar um problema se o sistema operacional for afetado.
Os desenvolvedores podem ficar sobrecarregados ao gerenciar milhares de contêineres.
A conversão de aplicativos monolíticos legados pode ser um processo complexo, e os desenvolvedores podem ter dificuldade em dimensionar corretamente os contêineres para cenários específicos.

A seguir, vamos conhecer alguns dos fornecedores de contêineres mais populares.

*Fornecedores de container

O Docker é uma plataforma robusta e a plataforma de contêineres mais popular atualmente.
O Podman é um mecanismo de contêiner sem daemon que é mais seguro que o Docker.
Os desenvolvedores geralmente preferem o LXC para aplicações e operações com uso intenso de dados.

E o Vagrant oferece os mais altos níveis de isolamento na máquina física em execução.

*Conclusão

Vimos que as organizações estão migrando para contêineres para superar os desafios de isolamento, utilização, provisionamento, desempenho e muito mais.

Um contêiner é uma unidade padrão de software que encapsula tudo o que é necessário para criar , enviar e executar aplicativos.
Os contêineres são independentes do sistema operacional, da linguagem de programação e da plataforma.
Eles reduzem o tempo e os custos de implantação, melhoram a utilização, automatizam processos e oferecem suporte a aplicativos de próxima geração (microsserviços).

Os desenvolvedores podem descobrir que o gerenciamento, a migração de projetos antigos e o dimensionamento correto são desafios significativos.
E, finalmente, os principais fornecedores de contêineres incluem Docker, Podman, LXC e Vagrant.